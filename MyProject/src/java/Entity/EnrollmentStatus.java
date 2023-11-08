/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Phạm Văn Nghĩa
 */
public class EnrollmentStatus extends BaseEntity{
    
 private String enrollmentId;
    private String status;


    public EnrollmentStatus(String enrollmentId, String status) {
        this.enrollmentId = enrollmentId;
        this.status = status;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
