import sqlite3
import os

class UserManager:
    ## register user
    def __init__(self, db_path, welcome_folder):
        self.db_path = db_path
        self.welcome_folder = welcome_folder
        ## makes call to initialize db
        self._init_db()

    def _init_db(self):
        ## creates a table at the provided db path
        with sqlite3.connect(self.db_path) as conn:
            conn.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)")

    ## function to register user.
    ## takes user_id and name.
    def register_user(self, user_id, name):
        # 1. Save to Database
        with sqlite3.connect(self.db_path) as conn:
            conn.execute("INSERT INTO users (id, name) VALUES (?, ?)", (user_id, name))
        
        # 2. Save to File System
        file_path = os.path.join(self.welcome_folder, f"welcome_{user_id}.txt")
        with open(file_path, "w") as f:
            f.write(f"Hello {name}, welcome to our service!")

    ## featches record from the database
    def get_user_name(self, user_id):
        with sqlite3.connect(self.db_path) as conn:
            cursor = conn.execute("SELECT name FROM users WHERE id = ?", (user_id,))
            row = cursor.fetchone()
            return row[0] if row else None
