class Database:
    def get_user_points(self, user_id):
        pass


def calculate_discount(user_id, db_instance):
    points = db_instance.get_user_points(user_id)
    if points > 100:
        return 0.20
    return 0.05
