package com.nuc.zp.quertz2.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nuc.zp.quertz2.util.IsCron;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "t_job")
public class MyJob implements Serializable {

    private static final long serialVersionUID = 400066840871805700L;

    /**
     * 任务调度参数 key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL("0"),
        /**
         * 暂停
         */
        PAUSE("1");

        private String value;

        ScheduleStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue
    private Integer jobId;

    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    private String beanName;

    //    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    private String methodName;

    @Size(max = 50, message = "{noMoreThan}")
    private String params;

    @NotBlank(message = "{required}")
    @IsCron(message = "{importnvalid}")
    private String cronExpression;


    @Size(max = 100, message = "{noMoreThan}")
    private String remark;

    @JsonFormat(pattern="yyyyMMddHHmmss", timezone="GMT+8")
    @JSONField(format = "yyyyMMddHHmmss")
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date startTime;

    @Future
    @JSONField(format = "yyyyMMddHHmmss")
    @JsonFormat(pattern="yyyyMMddHHmmss", timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Date endTime;

    private String status = ScheduleStatus.NORMAL.value;
}
