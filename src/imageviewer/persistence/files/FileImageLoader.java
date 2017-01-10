package imageviewer.persistence.files;

import imageviewer.model.Image;
import imageviewer.persistence.ImageLoader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FileImageLoader implements ImageLoader {
    
    private List<byte[]> images = new ArrayList<>();
    
    public FileImageLoader(String name) {
        try {
            loadImagesFrom(name);
        } 
        catch (ClassNotFoundException | SQLException ex) {
        }
    }
    
    @Override
    public Image load() {
        return imageAt(0);
    }

    private void loadImagesFrom(String name) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + name); 
            Statement statement = connection.createStatement()) {
            statement.execute("SELECT * FROM paisaje;");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                images.add(resultSet.getBytes("image"));
            }
        }
    }

    private Image imageAt(final int index) {
        return new Image() {

            @Override
            public InputStream inputStream() {
                return new ByteArrayInputStream(images.get(index));
            }

            @Override
            public Image next() {
                if (index == images.size() - 1) return imageAt(0);
                return imageAt(index + 1);
            }

            @Override
            public Image prev() {
                if (index == 0) return imageAt(images.size() - 1);
                return imageAt(index - 1);
            }
        };
    }
    
}
