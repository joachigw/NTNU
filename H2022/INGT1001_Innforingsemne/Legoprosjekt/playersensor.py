class PlayerSensor:
    def __init__(self, sensor):
        self.sensor = sensor
        self.has_been_pressed = False
        self.time = 0
