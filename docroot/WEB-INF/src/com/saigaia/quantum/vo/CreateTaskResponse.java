package com.saigaia.quantum.vo;

public class CreateTaskResponse
{
    private String id;

    private Input_files[] input_files;

    private String application;

    private String[] arguments;

    private String status;

    private String description;

    private Output_files[] output_files;

    private _links[] _links;

    private String date;

    private String user;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
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

    public _links[] get_links ()
    {
        return _links;
    }

    public void set_links (_links[] _links)
    {
        this._links = _links;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getUser ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", input_files = "+input_files+", application = "+application+", arguments = "+arguments+", status = "+status+", description = "+description+", output_files = "+output_files+", _links = "+_links+", date = "+date+", user = "+user+"]";
    }
}