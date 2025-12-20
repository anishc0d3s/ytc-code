import os
import subprocess
import multiprocessing
import glob

def run_rsync(file_path):
    destination = './dir2'
    print(f"Transferring file {file_path}...")
    rsync_command = ['rsync', '-av', file_path, destination]
    subprocess.run(rsync_command)


if __name__ == '__main__':
    src = "/Volumes/Seagate Backup Plus Drive 1/CBT*/*"

    file_list = glob.glob(src)
    num_processes = os.cpu_count()

    with multiprocessing.Pool(processes=1) as pool:
        pool.starmap(run_rsync, [(os.path.join(src, file),) for file in file_list])

    print("All files have been transferred.")

