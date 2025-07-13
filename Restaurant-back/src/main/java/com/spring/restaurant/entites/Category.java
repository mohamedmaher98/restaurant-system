package com.spring.restaurant.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity
{
	private String flag;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;
}
