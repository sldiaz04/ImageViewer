package imageviewer.model;

import java.io.InputStream;

public interface Image {

    InputStream inputStream();
    Image next();
    Image prev();

    
}
