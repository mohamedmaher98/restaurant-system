package com.spring.restaurant.entites;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Chef extends BaseEntity
{
	private String specialty;
	private String facebookLink;
	private String twitterLink;
	private String instagramLink;
}
