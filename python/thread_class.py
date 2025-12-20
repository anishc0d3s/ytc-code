import threading
import time

class DownloadThread(threading.Thread):

    def __init__(self, file_name):
        super().__init__()
        self.file_name = file_name

    def run(self):
        print(f"Starting download: {self.file_name}")
        time.sleep(2) # simulate download
        print(f"Complete download: {self.file_name}")


threads = []
files = ["files1.txt", "files2.txt", "files3.txt"]


for file in files:
    thread = DownloadThread(file)
    thread.start()
    threads.append(thread)


# Wait for all threads to complete
for thread in threads:
    thread.join()
