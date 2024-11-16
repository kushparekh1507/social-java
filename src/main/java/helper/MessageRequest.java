/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import java.util.List;

/**
 *
 * @author DELL
 */
public class MessageRequest {
    private List<Integer> participantsIds;
    private Integer groupId;
    private Integer senderId;
    private String text;

    public List<Integer> getParticipantsIds() {
        return participantsIds;
    }

    public void setParticipantsIds(List<Integer> participantsIds) {
        this.participantsIds = participantsIds;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
}
