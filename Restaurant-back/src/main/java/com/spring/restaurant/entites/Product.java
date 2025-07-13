package com.spring.restaurant.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity
{
	@Column(length = 1000)
	private String description;

	private Double price;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
}
