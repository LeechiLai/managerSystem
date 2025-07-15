package com.cndc.business.domain;

/**
 * @Author laizhiying
 * 用于接收前端传递过来的数据
 */
public class BatchAddProfessionalAllocationDTO {

    private ProfessionalAllocation professionalAllocation;
    private Long itemId;

    public ProfessionalAllocation getProfessionalAllocation() {
        return professionalAllocation;
    }

    public void setProfessionalAllocation(ProfessionalAllocation professionalAllocation) {
        this.professionalAllocation = professionalAllocation;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
