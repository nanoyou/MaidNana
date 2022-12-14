package com.github.nanoyou.maidnana.dao;

import com.github.nanoyou.maidnana.MaidNana;
import com.github.nanoyou.maidnana.entity.Identifiable;
import com.github.nanoyou.maidnana.util.GsonUtil;
import com.github.nanoyou.maidnana.util.observer.ConcreteSubject;
import com.github.nanoyou.maidnana.util.observer.Observer;
import com.github.nanoyou.maidnana.util.observer.Subject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public abstract class BaseDao<T extends Identifiable> implements Subject {
    public abstract Path getPath();
    public abstract Type getType();
    private final Map<UUID, T> data = new HashMap<>();

    public BaseDao() {
        if (Files.notExists(getPath())) {
            MaidNana.INSTANCE.getLogger().info("正在创建默认配置文件 " + getPath().toString());
            save();
            return;
        }
        load();
    }

    /**
     * 添加值
     *
     * @param value 要添加的值
     */
    public void add(T value) {
        synchronized (this) {
            data.put(value.getUuid(), value);
            save();
            notifyObservers();
        }
    }

    /**
     * 获取值
     *
     * @param id uuid
     * @return 取得的值
     */
    public Optional<T> get(UUID id) {
        T r;
        synchronized (this) {
            r = data.get(id);
        }
        if (r == null) {
            return Optional.empty();
        }
        return Optional.of(r);
    }

    /**
     * 修改值, 会替换原来的值
     *
     * @param value 新的值
     * @return  修改前的值
     */
    public T modify(T value) {
        T r;
        synchronized (this) {
            r = data.put(value.getUuid(), value);
            save();
            notifyObservers();
        }
        return r;
    }

    /**
     * 删除值
     *
     * @param id UUID
     * @return 被删除的值, 若未找到返回空
     */
    public Optional<T> delete(UUID id) {
        T r;
        synchronized (this) {
            r = data.remove(id);
            if (r == null) {
                return Optional.empty();
            }
            save();
            notifyObservers();
        }
        return Optional.of(r);
    }

    /**
     * 返回所有值
     *
     * @return 全部值
     */
    public List<T> getAll() {
        synchronized (this) {
            return new ArrayList<>(data.values());
        }
    }

    private void load() {
        String jsonStr;
        try {
            jsonStr = Files.readString(getPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            MaidNana.INSTANCE.getLogger().error("无法读入 " + getPath().toString(), e);
            throw new RuntimeException(e);
        }
        Collection<T> r = GsonUtil.gson.fromJson(jsonStr, getType());
        data.clear();
        r.forEach(v -> data.put(v.getUuid(), v));
    }
    private void save() {
        var jsonStr = GsonUtil.gson.toJson(data.values(), getType());
        try {
            Files.writeString(getPath(), jsonStr, StandardCharsets.UTF_8);
        } catch (IOException e) {
            MaidNana.INSTANCE.getLogger().error("无法写入 " + getPath().toString(), e);
            throw new RuntimeException(e);
        }
    }

    private final Subject subject = new ConcreteSubject();

    @Override
    public void registerObserver(Observer observer) {
        subject.registerObserver(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        subject.removeObserver(observer);
    }

    @Override
    public void notifyObservers() {
        subject.notifyObservers();
    }
}
