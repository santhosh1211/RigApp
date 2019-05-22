package in.co.santhoshiot.rigapp.Dto;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("response")
    private String Response;
    @SerializedName("status")
    private Boolean Status;
    @SerializedName("id")
    private String Id;
    @SerializedName("phone")
    private String Phone;
    @SerializedName("emp_id")
    private String Emp_id;
    @SerializedName("desig")
    private String Desig;
    @SerializedName("center")
    private String Center;
    @SerializedName("sro")
    private String Sro;
    @SerializedName("name")
    private String Name;
    @SerializedName("image")
    private String image;
    @SerializedName("zone")
    private String Zone;
    @SerializedName("rank")
    private String Rank;
    @SerializedName("login")
    private String Login;
    @SerializedName("success")
    private String success;
    @SerializedName("message")
    private String message;

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @SerializedName("power")
    private String power;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @SerializedName("centername")
    private String centername;
    @SerializedName("zonename")
    private String zonename;
    @SerializedName("totalcount")
    private String totalcount;
    @SerializedName("remaincount")
    private String remaincount;
    @SerializedName("emptycount")
    private String emptycount;
    @SerializedName("qccompleted")
    private String qccompleted;
    @SerializedName("uploaded")
    private String uploaded;
    @SerializedName("systems")
    private String systems;
    @SerializedName("scanners")
    private String scanners;
    @SerializedName("currentdate")
    private String currentdate;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("todayattendance")
    private String todayattendance;
    @SerializedName("logintime")
    private String logintime;
    @SerializedName("breaktime")
    private String breaktime;
    @SerializedName("date")
    private String date;
    @SerializedName("reason")
    private String reason;
    @SerializedName("leavestatus")
    private String leavestatus;

    public String getLeavestatus() {
        return leavestatus;
    }

    public void setLeavestatus(String leavestatus) {
        this.leavestatus = leavestatus;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(String breaktime) {
        this.breaktime = breaktime;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public String getTodayattendance() {
        return todayattendance;
    }

    public void setTodayattendance(String todayattendance) {
        this.todayattendance = todayattendance;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }



    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getSystems() {
        return systems;
    }

    public void setSystems(String ystems) {
        this.systems = ystems;
    }

    public String getScanners() {
        return scanners;
    }

    public void setScanners(String scanners) {
        this.scanners = scanners;
    }

    public String getCentername() {
        return centername;
    }

    public void setCentername(String centername) {
        this.centername = centername;
    }

    public String getZonename() {
        return zonename;
    }

    public void setZonename(String zonename) {
        this.zonename = zonename;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public String getRemaincount() {
        return remaincount;
    }

    public void setRemaincount(String remaincount) {
        this.remaincount = remaincount;
    }

    public String getEmptycount() {
        return emptycount;
    }

    public void setEmptycount(String emptycount) {
        this.emptycount = emptycount;
    }

    public String getQccompleted() {
        return qccompleted;
    }

    public void setQccompleted(String qccompleted) {
        this.qccompleted = qccompleted;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }



    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmp_id() {
        return Emp_id;
    }

    public void setEmp_id(String emp_id) {
        Emp_id = emp_id;
    }

    public String getDesig() {
        return Desig;
    }

    public void setDesig(String desig) {
        Desig = desig;
    }

    public String getCenter() {
        return Center;
    }

    public void setCenter(String center) {
        Center = center;
    }

    public String getSro() {
        return Sro;
    }

    public void setSro(String sro) {
        Sro = sro;
    }




    public void setResponse(String response) {
        Response = response;
    }

    public  void setStatus(Boolean status){
        Status=status;
    }
    public String getResponse() {
        return Response;
    }
    public Boolean getStatus(){
        return Status;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String _image) {
        image = _image;
    }
}
