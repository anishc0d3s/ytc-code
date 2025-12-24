## Integration Tests

import pytest
import os
import tempfile
import shutil
from app import UserManager

### Test Fixture that sets up the environment
@pytest.fixture
def test_env():
    # SETUP: Create a temporary directory for files
    db_dir = tempfile.mkdtemp()
    db_file = os.path.join(db_dir, "test_users.db")
    os.makedirs(db_dir, exist_ok=True)

    print(db_dir)
    # Initialize the manager with real paths
    # this will initialize the db
    manager = UserManager(db_file, db_dir)
    
    yield manager, db_dir  # This is where the test runs
    
    # TEARDOWN: Delete the temporary files and folder
    # shutil.rmtree(db_dir)

def test_full_registration_flow(test_env):
    manager, db_dir = test_env
    user_id = 101
    user_name = "Alice"

    # EXECUTION: Call the real method
    manager.register_user(user_id, user_name)

    # ASSERTION 1: Verify Database Integration
    # We check if the data actually exists in the DB
    assert manager.get_user_name(user_id) == "Alice"

    # ASSERTION 2: Verify File System Integration
    # We check if the actual file was created on the disk
    expected_file = os.path.join(db_dir, f"welcome_{user_id}.txt")
    assert os.path.exists(expected_file)
    
    with open(expected_file, "r") as f:
        content = f.read()
        assert "Hello Alice" in content
