package com.saigaia.quantum.vo;

public class Output_files
{
    private String name;

    private String url;

    
    public Output_files(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", url = "+url+"]";
    }
}
