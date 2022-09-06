package com.github.nanoyou.maidnana.service;

import com.github.nanoyou.maidnana.MaidNana;
import com.github.nanoyou.maidnana.dao.AnnouncementDao;
import com.github.nanoyou.maidnana.dao.TemplateDao;
import com.github.nanoyou.maidnana.entity.Announcement;
import com.github.nanoyou.maidnana.entity.Body;
import com.github.nanoyou.maidnana.entity.Template;
import com.github.nanoyou.maidnana.entity.Trigger;
import it.sauronsoftware.cron4j.InvalidPatternException;
import it.sauronsoftware.cron4j.Scheduler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.Mirai;
import org.jetbrains.annotations.TestOnly;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AnnouncementService {
    private final static AnnouncementService instance = new AnnouncementService();
    private final static AnnouncementDao dao = AnnouncementDao.getInstance();

    public static AnnouncementService getInstance() {
        return instance;
    }

    private final Scheduler scheduler = new Scheduler();

    private void flushTasks() {
        var logger = MaidNana.INSTANCE.getLogger();

        logger.info("刷新任务");
        if (scheduler.isStarted()) {
            logger.info("关闭现有的任务");
            scheduler.stop();
        }

        Bot.getInstances().forEach(bot -> {
            logger.info("Bot: " + bot.getId());
        });

        AnnouncementDao.getInstance()
                .getAll().stream()
                .filter(ann -> ann.isEnabled()
                        && ann.getBody() != null
                        && !ann.getGroups().isEmpty()
                        && !ann.getTriggers().isEmpty()
                ).forEach(ann -> ann.getTriggers().forEach(trigger -> {
                    try {
                        logger.info("添加公告: " + ann.getUuid());
                        logger.info("群: " + ann.getGroups());
                        scheduler.schedule(trigger.getCron(), () -> {
                            Bot.getInstances().forEach(bot -> ann.getGroups().forEach(group -> {
                                logger.info("尝试获取组");
                                var g = bot.getGroup(group);
                                logger.info("获取完成");
                                if (g == null) return;

                                logger.info("尝试发送公告");
                                g.sendMessage(ann.getBody().getBodyString());
                            }));
                        });
                    } catch (InvalidPatternException ignore) {
                        logger.warning("cron 表达式格式错误: " + trigger.getCron());
                    }
                }));

        scheduler.start();
    }
    public void init() {
        flushTasks();
        AnnouncementDao.getInstance().registerObserver(this::flushTasks);
        TemplateDao.getInstance().registerObserver(this::flushTasks);
    }

    /**
     * 创建公告
     * @return 创建的公告
     */
    public Announcement create() {
        var ansment = new Announcement();
        ansment.setUuid(UUID.randomUUID());
        dao.add(ansment);
        return ansment;
    }
    /**
     * 创建公告
     * @param alias 公告别名
     * @return 创建的公告, 若别名已存在返回空
     */
    public Optional<Announcement> create(String alias) {
        if (dao.get(alias).isPresent()) {
            return Optional.empty();
        }
        var ann = new Announcement();
        ann.setUuid(UUID.randomUUID());
        ann.setAlias(alias);
        dao.add(ann);
        return Optional.of(ann);
    }

    /**
     * 获取公告
     * @param announcementID ID
     * @return 找到返回值, 未找到返回空
     */
    public Optional<Announcement> get(UUID announcementID) {
        return dao.get(announcementID);
    }
    /**
     * 获取公告
     * @param alias 别名
     * @return 找到返回值, 未找到返回空
     */
    public Optional<Announcement> get(String alias) {
        return dao.get(alias);
    }

    /**
     * 删除公告
     * @param announcementID 公告ID
     * @return 被删的公告
     */
    public Optional<Announcement> delete(UUID announcementID) {

        return dao.delete(announcementID);
    }

    /**
     * 增加分组
     * @param announcementID 公告ID
     * @param groupID 分组ID
     */
    public Optional<Announcement> addGroup(UUID announcementID, long groupID) {
        var ansment = get(announcementID);
        if(ansment.isPresent()){
            var groups = ansment.get().getGroups();
            if (groups.stream().noneMatch(e -> e.equals(groupID)))
                groups.add(groupID);

            ansment.get().setGroups(groups);
            dao.modify(ansment.get());
        }
        return ansment;
    }
    /**
     * 增加分组
     * @param alias 公告ID
     * @param groupID 分组ID
     */
    public Optional<Announcement> addGroup(String alias, long groupID) {
        var ansment = get(alias);
        if(ansment.isPresent()){
            var groups = ansment.get().getGroups();
            if (groups.stream().noneMatch(e -> e.equals(groupID)))
                groups.add(groupID);

            ansment.get().setGroups(groups);
            dao.modify(ansment.get());
        }
        return ansment;
    }

    /**
     * 删除分组
     * @param announcementID 公告ID
     * @param groupID 分组ID
     * @return 被删除分组的公告, 未找到返回空
     */
    public Optional<Announcement> removeGroup(UUID announcementID, long groupID) {
        var ansment = get(announcementID);
        if(ansment.isPresent()){
            var groups = ansment.get().getGroups();
            if (groups.stream().noneMatch(e -> e.equals(groupID)))
                return Optional.empty();

            groups.removeIf(e -> e.equals(groupID));
            ansment.get().setGroups(groups);
            dao.modify(ansment.get());
        }
        return ansment;
    }
    /**
     * 删除分组
     *
     * @param alias   别名
     * @param groupID 组id
     * @return {@link Optional}<{@link Announcement}>
     */
    public Optional<Announcement> removeGroup(String alias, long groupID) {
        var ansment = get(alias);
        if(ansment.isPresent()){
            var groups = ansment.get().getGroups();
            if (groups.stream().noneMatch(e -> e.equals(groupID)))
                return Optional.empty();

            groups.removeIf(e -> e.equals(groupID));
            ansment.get().setGroups(groups);
            dao.modify(ansment.get());
        }
        return ansment;
    }

    /**
     * 添加触发器
     *
     * @param announcementID 公告ID
     * @param trigger        触发器
     */
    public Optional<Announcement> addTrigger(UUID announcementID, Trigger trigger) {
        var ansment = get(announcementID);
        if(ansment.isPresent()){
            var triggers = ansment.get().getTriggers();
            if (triggers.stream().noneMatch(e -> e.equals(trigger)))
                triggers.add(trigger);

            ansment.get().setTriggers(triggers);
            dao.modify(ansment.get());
        }

        return ansment;
    }
    /**
     * 添加触发器
     *
     * @param alias 别名
     * @param trigger        触发器
     */
    public Optional<Announcement> addTrigger(String alias, Trigger trigger) {
        var ansment = get(alias);
        if(ansment.isPresent()){
            var triggers = ansment.get().getTriggers();
            if (triggers.stream().noneMatch(e -> e.equals(trigger)))
                triggers.add(trigger);

            ansment.get().setTriggers(triggers);
            dao.modify(ansment.get());
        }

        return ansment;
    }

    /**
     * 移除触发器
     *
     * @param announcementID 公告ID
     * @param triggerID      触发器ID
     * @return 修改后的公告，若找不到则返回空容器？
     */
    public Optional<Announcement> removeTrigger(UUID announcementID, UUID triggerID) {
        var ansment = get(announcementID);
        if(ansment.isPresent()){
            var triggers = ansment.get().getTriggers();
            if (triggers.stream().noneMatch(e -> e.getUuid().equals(triggerID)))
                return Optional.empty();

            triggers.removeIf(e->e.getUuid().equals(triggerID));
            ansment.get().setTriggers(triggers);
            dao.modify(ansment.get());
        }
        return ansment;
    }
    /** 移除触发器
     *
     * @param alias 别名
     * @param triggerID 触发器ID
     * @return 修改后的公告，若找不到则返回空容器？
     */
    public Optional<Announcement> removeTrigger(String alias, UUID triggerID) {
        var ansment = get(alias);
        if(ansment.isPresent()){
            var triggers = ansment.get().getTriggers();
            if (triggers.stream().noneMatch(e -> e.getUuid().equals(triggerID)))
                return Optional.empty();

            triggers.removeIf(e->e.getUuid().equals(triggerID));
            ansment.get().setTriggers(triggers);
            dao.modify(ansment.get());
        }
        return ansment;
    }

    /**
     * 设置身体
     * @param announcementID 公告ID
     * @param body 身体
     */
    public Optional<Announcement> setBody(UUID announcementID, Body body) {
        var ansment = get(announcementID);
        ansment.ifPresent(a ->{
            a.setBody(body);
            dao.modify(a);
        });
        return ansment;
    }
    /**
     * 设置身体
     * @param alias 别名
     * @param body 身体
     */
    public Optional<Announcement> setBody(String alias, Body body){
        var ansment = get(alias);
        ansment.ifPresent(a ->{
            a.setBody(body);
            dao.modify(a);
        });
        return ansment;
    }

    /**
     * 启用公告
     * @param announcementID 公告ID
     */
    public Optional<Announcement> enable(UUID announcementID) {
        var ansment = get(announcementID);
        ansment.ifPresent(a ->{
            a.setEnabled(true);
            dao.modify(a);
        });
        return ansment;
    }
    /**
     * 启用公告
     * @param alias 别名捏
     */
    public Optional<Announcement> enable(String alias) {
        var ansment = get(alias);
        ansment.ifPresent(a ->{
            a.setEnabled(true);
            dao.modify(a);
        });
        return ansment;
    }

    /**
     * 禁用公告
     * @param announcementID 公告ID
     */
    public Optional<Announcement> disable(UUID announcementID) {
        var ansment = get(announcementID);
        ansment.ifPresent(a ->{
            a.setEnabled(false);
            dao.modify(a);
        });
        return ansment;
    }
    /**
     * 禁用公告
     * @param alias 别名
     */
    public Optional<Announcement> disable(String alias) {
        var ansment = get(alias);
        ansment.ifPresent(a ->{
            a.setEnabled(false);
            dao.modify(a);
        });
        return ansment;
    }

    /**
     * 得到所有
     *
     * @return {@link List}<{@link Announcement}>
     */
    public List<Announcement> getAll(){
        return dao.getAll();
    }
}
