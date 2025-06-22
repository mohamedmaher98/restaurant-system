package com.spring.restaurant.entites;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category
{
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	private String name;

	private String logo;

	private String flag;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;
}
