package CSV.GUI;

class FileFilterExt extends javax.swing.filechooser.FileFilter
{
    String extension  ;
    String description;

    FileFilterExt(String extension, String descr)
    {
        this.extension = extension;
        this.description = descr;
    }
    @Override
    public boolean accept(java.io.File file)
    {
        if(file != null) {
            if (file.isDirectory())
                return true;
            if( extension == null )
                return (extension.length() == 0);
            return file.getName().endsWith(extension);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return description;
    }
}