package com.saigaia.quantum.vo;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskRequest {
	public String application;
	public String description;
	public List<String> arguments = new ArrayList<String>();
	public List<Output_files> output_files = new ArrayList<Output_files>();
	public List<Input_files> input_files = new ArrayList<Input_files>();
}
