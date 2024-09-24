package pia.task.library_automation.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee implements UserDetails {

	@Id
	@Column(name = "id_employee")
	private int employeeId;

	@Column(name = "user_password")
	private String password;

	@Column(name = "first_name_employee")
	private String employeeFirstName;

	@Column(name = "last_name_employee")
	private String employeeLastName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "registration_date_employee")
	private LocalDate registrationDate;

	@Column(name = "daily_wage_employee")
	private double dailyWage;

	@OneToOne
	@JoinColumn(name = "name_role")
	private Role role;


	public Employee(){}

	public Employee(int employeeId, String password, String employeeFirstName, String employeeLastName, LocalDate registrationDate, double dailyWage, Role role) {
		this.employeeId = employeeId;
		this.password = password;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.registrationDate = registrationDate;
		this.dailyWage = dailyWage;
		this.role = role;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return String.valueOf(employeeId);
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public double getDailyWage() {
		return dailyWage;
	}

	public void setDailyWage(double dailyWage) {
		this.dailyWage = dailyWage;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
