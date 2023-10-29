#!/usr/bin/env pybricks-micropython
import random
from playersensor import PlayerSensor
from player import Player
from table2 import Table
import distractions
import time
from random import randint
from pybricks.hubs import EV3Brick
from pybricks.ev3devices import (Motor, TouchSensor, ColorSensor, InfraredSensor, UltrasonicSensor, GyroSensor)
from pybricks.parameters import Port, Stop, Direction, Button, Color
from pybricks.tools import wait, StopWatch, DataLog
from pybricks.robotics import DriveBase
from pybricks.media.ev3dev import SoundFile, ImageFile


def time_now_millis():
    return time.time() * 1000


next_distraction = randint(4000, 10000) + time_now_millis()
distractions_running = False
def distract_players():
    global next_distraction
    global distractions_running

    if distractions_running:
        completed = distractions.perform_distractions() # The function returns True if the distraction program has completed
        if completed:
            distractions_running = False
            next_distraction = randint(4000, 10000) + time_now_millis() # Set the next time for a distraction to occur. 
    
    if next_distraction <= time_now_millis():
        distractions_running = True


def update_all_players():
    for p in players:
        p.update_game_score()
        p.update_game_times()
        p.reset_set()


def are_all_sensors_pressed():
    for p in players:
        if not p.player_sensor.has_been_pressed:
            return False
        return True


def update_pressed():
    for p in players:
        if p.player_sensor.sensor.pressed() and not p.player_sensor.has_been_pressed:
            p.set_times.append(watch.time())
            # uses f(x)=190000/x + 100, lines up with human limit.
            p.set_scores.append((190000//watch.time()) + 100)
            p.player_sensor.has_been_pressed = True
            p.player_sensor.time = watch.time()

ev3 = EV3Brick()

right_motor = Motor(Port.A)
left_motor = Motor(Port.B)
robot = DriveBase(left_motor, right_motor, wheel_diameter=56, axle_track=114)

distraction = distractions(left_motor, right_motor, robot)

ev3.light.on(Color.RED)
wait(500)


player1 = Player(PlayerSensor(TouchSensor(Port.S1)), "Jens")
player2 = Player(PlayerSensor(TouchSensor(Port.S2)), "Kristians")
player3 = Player(PlayerSensor(TouchSensor(Port.S3)), "Marius")
player4 = Player(PlayerSensor(TouchSensor(Port.S4)), "Haakon")
players = [player1, player2, player3, player4]

NUMBER_OF_SETS = 3
ROUNDS_IN_SET = 3
MIN_TIME_INTERVAL = 750
MAX_TIME_INTERVAL = 1500

for i in range(NUMBER_OF_SETS):

    for j in range(ROUNDS_IN_SET):

        ev3.light.on(Color.ORANGE)

        # Kode for å diskvalifisere bruker
        LIGHT_IS_GREEN = False
        wait(random.randint(MIN_TIME_INTERVAL, MAX_TIME_INTERVAL))
        LIGHT_IS_GREEN = True

        while not (LIGHT_IS_GREEN):
            update_pressed()
            for p in players:
                if p.player_sensor.has_been_pressed:
                    p.disqualification()

        # while (i < random.randInt(500, 1000)):
        #     if(are_any_sensors_pressed(touch_sensor1, touch_sensor2, touch_sensor3, touch_sensor4)):
        #         lower_score()
        watch = StopWatch()

        # def are_any_sensors_pressed(sensor1, sensor2, sensor3, sensor4):
        #     if sensor1.has_been_pressed:
        #     elsensor2.has_been_pressed or sensor3.has_been_pressed or sensor4.has_been_pressed

        ev3.light.on(Color.GREEN)
        while not (are_all_sensors_pressed()):
            update_pressed()
            distract_players()
            continue

        for p in players:
            print(p.player_sensor.time)

        ev3.light.on(Color.RED)

        for player in players:
            player.reset_has_been_pressed()

    print("Set scores:")
    print(player1.player_name, ": ", player1.get_total_set_score())
    print(player2.player_name, ": ", player2.get_total_set_score())
    print(player3.player_name, ": ", player3.get_total_set_score())
    print(player4.player_name, ": ", player4.get_total_set_score())

    print("--------------------\nAverage set scores:")

    print(player1.player_name, ": ", player1.get_average_set_score())
    print(player2.player_name, ": ", player2.get_average_set_score())
    print(player3.player_name, ": ", player3.get_average_set_score())
    print(player4.player_name, ": ", player4.get_average_set_score())

    update_all_players()

print("--------------------\nTotal 3-game scores:")
print(player1.player_name, ": ", player1.get_total_game_score())
print(player2.player_name, ": ", player2.get_total_game_score())
print(player3.player_name, ": ", player3.get_total_game_score())
print(player4.player_name, ": ", player4.get_total_game_score())
print("--------------------\nTotal 3-game average reaction times (ms)")
print(player1.player_name, ": ", player1.get_average_game_time())
print(player2.player_name, ": ", player2.get_average_game_time())
print(player3.player_name, ": ", player3.get_average_game_time())
print(player4.player_name, ": ", player4.get_average_game_time())
