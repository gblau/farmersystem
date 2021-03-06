package com.gb.common.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Appointment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appointment.id
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appointment.time
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    private Date time;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appointment.arrival_time
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appointment.app_person
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    private String appPerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appointment.is_accepted
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    private Byte isAccepted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appointment.user_id
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appointment.town_id
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    private Integer townId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appointment.id
     *
     * @return the value of appointment.id
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appointment.id
     *
     * @param id the value for appointment.id
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appointment.time
     *
     * @return the value of appointment.time
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appointment.time
     *
     * @param time the value for appointment.time
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appointment.arrival_time
     *
     * @return the value of appointment.arrival_time
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public Date getArrivalTime() {
        return arrivalTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appointment.arrival_time
     *
     * @param arrivalTime the value for appointment.arrival_time
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appointment.app_person
     *
     * @return the value of appointment.app_person
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public String getAppPerson() {
        return appPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appointment.app_person
     *
     * @param appPerson the value for appointment.app_person
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public void setAppPerson(String appPerson) {
        this.appPerson = appPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appointment.is_accepted
     *
     * @return the value of appointment.is_accepted
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public Byte isAccepted() {
        return isAccepted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appointment.is_accepted
     *
     * @param isAccepted the value for appointment.is_accepted
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public void setIsAccepted(Byte isAccepted) {
        this.isAccepted = isAccepted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appointment.user_id
     *
     * @return the value of appointment.user_id
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appointment.user_id
     *
     * @param userId the value for appointment.user_id
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appointment.town_id
     *
     * @return the value of appointment.town_id
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public Integer getTownId() {
        return townId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appointment.town_id
     *
     * @param townId the value for appointment.town_id
     *
     * @mbggenerated Thu May 16 17:34:09 CST 2019
     */
    public void setTownId(Integer townId) {
        this.townId = townId;
    }
}