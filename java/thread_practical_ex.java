class FileDownloader implements Runnable {
    private String fileName;
    private int fileSize;
    
    public FileDownloader(String fileName, int fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }
    
    @Override
    public void run() {
        System.out.println("Starting download: " + fileName);
        int downloaded = 0;
        
        while (downloaded < fileSize) {
            downloaded += 10;
            int progress = (downloaded * 100) / fileSize;
            System.out.println(fileName + " - Progress: " + progress + "%");
            
            try {
                Thread.sleep(200); // Simulate download time
            } catch (InterruptedException e) {
                System.out.println("Download interrupted: " + fileName);
                return;
            }
        }
        
        System.out.println("Download complete: " + fileName);
    }
}

public class DownloadManager {
    public static void main(String[] args) {
        Thread download1 = new Thread(new FileDownloader("video.mp4", 100));
        Thread download2 = new Thread(new FileDownloader("music.mp3", 50));
        Thread download3 = new Thread(new FileDownloader("document.pdf", 30));
        
        download1.start();
        download2.start();
        download3.start();
        
        System.out.println("All downloads started!");
    }
}
