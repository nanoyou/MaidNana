package com.github.nanoyou.maidnana.dao;

import com.github.nanoyou.maidnana.entity.Aliasable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public abstract class AliasDao<T extends Aliasable> extends BaseDao<T> {
    private final Map<String, T> data = new HashMap<>();
    public AliasDao() {
        super();
        load();
    }
    private void load() {
        data.clear();
        getAll().stream()
                .filter(v -> v.getAlias() != null && !"".equals(v.getAlias()))
                .forEach(v -> data.put(v.getAlias(), v));
    }

    public Optional<T> get(String alias) {
        return Optional.ofNullable(data.get(alias));
    }

    public Optional<T> delete(String alias) {
        var t = data.get(alias);
        if (t == null) {
            return Optional.empty();
        }
        return super.delete(t.getUuid());
    }

    @Override
    public void add(T value) {
        super.add(value);
        load();
    }

    @Override
    public Optional<T> delete(UUID id) {
        var t = super.delete(id);
        load();
        return t;
    }

    @Override
    public T modify(T value) {
        var t =  super.modify(value);
        load();
        return t;
    }
}
