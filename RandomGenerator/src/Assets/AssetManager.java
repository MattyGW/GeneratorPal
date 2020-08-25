package Assets;

import DisplayClasses.InterfaceManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AssetManager {
    private  static AssetManager assetManager;
    String path = (new File("").getAbsolutePath());

    public AssetManager(InterfaceManager interfaceManager){
    }

    public ImageView getImageView(String string) throws FileNotFoundException {
        try {
            FileInputStream input = new FileInputStream(path + "\\src\\Assets\\" + string + ".png");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            return imageView;
        }
        catch (FileNotFoundException e){
            System.out.println("incorrect file name: " + string);
        }
        System.out.println("This should ever be printed. From getImageView");
        return null;
    }

    public static AssetManager getAssetManager() {
        return assetManager;
    }

    public static void setAssetManager(AssetManager assetManager) {
        AssetManager.assetManager = assetManager;
    }
}
