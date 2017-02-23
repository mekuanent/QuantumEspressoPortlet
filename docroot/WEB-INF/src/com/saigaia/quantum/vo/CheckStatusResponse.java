package com.saigaia.quantum.vo;

public class CheckStatusResponse
{
    private String id;

    private String iosandbox;

    private String last_change;

    private String creation;

    private Input_files[] input_files;

    private String application;

    private String[] arguments;

    private String status;

    private String description;

    private Output_files[] output_files;

    private String user;

    private String[] runtime_data;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIosandbox ()
    {
        return iosandbox;
    }

    public void setIosandbox (String iosandbox)
    {
        this.iosandbox = iosandbox;
    }

    public String getLast_change ()
    {
        return last_change;
    }

    public void setLast_change (String last_change)
    {
        this.last_change = last_change;
    }

    public String getCreation ()
    {
        return creation;
    }

    public void setCreation (String creation)
    {
        this.creation = creation;
    }

    public Input_files[] getInput_files ()
    {
        return input_files;
    }

    public void setInput_files (Input_files[] input_files)
    {
        this.input_files = input_files;
    }

    public String getApplication ()
    {
        return application;
    }

    public void setApplication (String application)
    {
        this.application = application;
    }

    public String[] getArguments ()
    {
        return arguments;
    }

    public void setArguments (String[] arguments)
    {
        this.arguments = arguments;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public Output_files[] getOutput_files ()
    {
        return output_files;
    }

    public void setOutput_files (Output_files[] output_files)
    {
        this.output_files = output_files;
    }

    public String getUser ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    public String[] getRuntime_data ()
    {
        return runtime_data;
    }

    public void setRuntime_data (String[] runtime_data)
    {
        this.runtime_data = runtime_data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", iosandbox = "+iosandbox+", last_change = "+last_change+", creation = "+creation+", input_files = "+input_files+", application = "+application+", arguments = "+arguments+", status = "+status+", description = "+description+", output_files = "+output_files+", user = "+user+", runtime_data = "+runtime_data+"]";
    }
}