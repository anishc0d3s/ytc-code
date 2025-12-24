from unittest.mock import patch

### Mocking get weather api call
@patch('my_weather_app.requests.get')
def test_get_temperature(mock_get):
    # STUBBING: We tell the mock exactly what to return
    mock_get.return_value.json.return_value = {"temp": 25}
    
    result = my_weather_app.get_temp_in_city("London")
    assert result == 25


### function called once with the given argument
@patch('my_app.email_service.send')
def test_signup_sends_email(mock_send):
    my_app.register_user("test@example.com")
    
    # MOCKING: Verifying the interaction
    mock_send.assert_called_once_with("test@example.com", "Welcome!")
