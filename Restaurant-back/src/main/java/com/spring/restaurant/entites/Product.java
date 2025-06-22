package com.spring.restaurant.entites;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product
{
	@Id
	@GeneratedValue
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	private String name;

	private String imagePath;

	@Column(length = 1000)
	private String description;

	private Double price;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

}
