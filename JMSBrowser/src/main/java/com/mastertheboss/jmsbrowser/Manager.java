package com.mastertheboss.jmsbrowser;

import com.mastertheboss.jmsbrowser.bean.MessageDTO;
import com.mastertheboss.jmsbrowser.bean.QueueDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Manager implements Serializable{

	@EJB EJBBrowser ejb;

	private String queue;
	private int testmessages;
	private List<MessageDTO> list;
	List<QueueDTO> listQueue;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public int getTestmessages() {
        return testmessages;
    }

    public List<QueueDTO> getListQueue() {
        return listQueue;
    }

    public void setListQueue(List<QueueDTO> listQueue) {
        this.listQueue = listQueue;
    }

    public void setTestmessages(int testmessages) {
        this.testmessages = testmessages;
    }

    public List<MessageDTO> getList() {
        return list;
    }

    public void setList(List<MessageDTO> list) {
        this.list = list;
    }
    
    public void search() {
        list = ejb.browseMessage(queue);
    }

    public void sendTestMessages() {
        ejb.sendTestMessages(queue, testmessages);
        search();
    }

    public void consumeMessages() {
        ejb.consumeMessages(queue);
        search();
    }

    @PostConstruct
    public void loadCombo() {
        listQueue = ejb.getListQueues();
    }
}
