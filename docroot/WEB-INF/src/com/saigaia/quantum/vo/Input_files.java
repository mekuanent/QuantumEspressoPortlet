package com.saigaia.quantum.vo;

public class Input_files
{
    private String status;

    private String name;

    private String url;
    
    public Input_files(String status, String name, String url) {
		super();
		this.status = status;
		this.name = name;
		this.url = url;
	}

	public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", name = "+name+"]";
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
