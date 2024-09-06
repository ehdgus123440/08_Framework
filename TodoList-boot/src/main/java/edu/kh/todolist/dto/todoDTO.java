package edu.kh.todolist.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class todoDTO {
	private int todoNo;
	private String todoTitle;
	private String todoCheck;
	private String todoDate;
}
