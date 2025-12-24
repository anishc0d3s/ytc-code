import unittest
from unittest.mock import MagicMock
from services import calculate_discount, Database


class TestDiscountService(unittest.TestCase):

    def test_calculate_discount_high_points(self):

        mock_db = MagicMock(spec=Database)

        mock_db.get_user_points.return_value = 150

        result = calculate_discount(user_id=42, db_instance=mock_db)

        self.assertEqual(result, 0.20)

        mock_db.get_user_points.assert_called_with(42)


    def test_calculate_discount_low_points(self):
        mock_db = MagicMock(spec=Database)
        
        # Stubbing a different scenario (low points)
        mock_db.get_user_points.return_value = 10
        
        result = calculate_discount(user_id=7, db_instance=mock_db)
        
        self.assertEqual(result, 0.05)
    
        mock_db.get_user_points.assert_called_with(7)


if __name__ == "__main__":
    unittest.main()
