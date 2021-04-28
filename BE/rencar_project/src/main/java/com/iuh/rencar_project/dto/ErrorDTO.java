/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO implements Serializable{

	private static final long serialVersionUID = 1334153051435798281L;

	private final String messages;

	private final HttpStatus status;

	private final LocalDateTime timestamp;
}
