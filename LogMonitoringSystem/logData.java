/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query_tool;

/**
 *
 * @author smitahirve
 */
public class logData {
    private String unix_time; 
    private String ip_address; 
    private String cpu_id; 
    private String cpu_usage; 

    public String getUnix_time() {
        return unix_time;
    }

    public String getIp_address() {
        return ip_address;
    }

    public String getCpu_id() {
        return cpu_id;
    }

    public String getCpu_usage() {
        return cpu_usage;
    }

    public void setUnix_time(String unix_time) {
        this.unix_time = unix_time;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public void setCpu_id(String cpu_id) {
        this.cpu_id = cpu_id;
    }

    public void setCpu_usage(String cpu_usage) {
        this.cpu_usage = cpu_usage;
    }

}
