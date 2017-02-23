package com.saigaia.quantum.vo;

public class UploadFilesResponse
{
    private String message;

    private String[] files;

    private String task;

    private String gestatus;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String[] getFiles ()
    {
        return files;
    }

    public void setFiles (String[] files)
    {
        this.files = files;
    }

    public String getTask ()
    {
        return task;
    }

    public void setTask (String task)
    {
        this.task = task;
    }

    public String getGestatus ()
    {
        return gestatus;
    }

    public void setGestatus (String gestatus)
    {
        this.gestatus = gestatus;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", files = "+files+", task = "+task+", gestatus = "+gestatus+"]";
    }
}