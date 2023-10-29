class Player:
    def __init__(self, player_sensor, player_name):
        self.player_sensor = player_sensor
        self.player_name = player_name
        self.set_times = []
        self.set_scores = []
        self.game_times = []
        self.game_scores = []

    def get_total_set_score(self):
        return sum(self.set_scores)

    def update_game_score(self):
        self.game_scores.append(self.set_scores)

    def update_game_times(self):
        self.game_times.append(self.set_times)

    def get_total_game_score(self):
        return sum(sum(self.game_scores, []))

    def get_average_set_score(self):
        return sum(self.set_scores)//3

    def get_average_set_time(self):
        return sum(self.set_times)//3

    def get_average_game_score(self):
        return sum(sum(self.game_scores, []))//3

    def get_average_game_time(self):
        return sum(sum(self.game_times, []))//9

    def reset_has_been_pressed(self):
        self.player_sensor.has_been_pressed = False

    def reset_set(self):
        self.set_times = []
        self.set_scores = []
    
    def disqualification(self):
        self.set_times[-1] = 2500
        self.set_scores[-1] = 0
