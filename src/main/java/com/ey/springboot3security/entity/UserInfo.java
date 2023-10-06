package com.ey.springboot3security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import com.ey.springboot3security.customvalidation.RoleValidType;
import com.ey.springboot3security.customvalidation.UniqueEmail;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "is required")
	@Email(message = "invalid email address")
	//@UniqueEmail //unique email -->checkes if it exists in the database
	private String email;

    
    @RoleValidType //custom validation
    private String roles ="ROLE_USER";

	@NotBlank(message = "password is required")
	@Size(min=4,message="password must be at least 4 characters")
	private String password;

	// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<Product> products; // Define the one-to-many relationship

	
    

}
