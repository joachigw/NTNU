from math import pi


class Distractions:
    def __init__(self, ev3, left_motor, right_motor, drivebase):
        self.ev3 = ev3
        self.left_motor = left_motor
        self.right_motor = right_motor
        self.drivebase = drivebase
        self.is_distracting = False

    def turn_around(self, ANGLE, TIME):
        self.is_distracting = True
        self.ev3.speaker.beep()
        self.drivebase.turn(ANGLE)
        wait(TIME)
        self.drivebase.turn(-ANGLE)

    # def follow_a_circle(self, CIRCLE_RADIUS):
    #     DISTANCE_CIRCLE = pi * 2/4 * CIRCLE_RADIUS
    #     self.drivebase.straight(CIRCLE_RADIUS)
    #     self.drivebase.turn(95)
    #     self.drivebase.reset()
    #     while(self.drivebase.angle() < 380):
    #         self.drivebase.drive(DISTANCE_CIRCLE, 95)
    #     self.drivebase.stop()
    #     self.drivebase.turn(-95)
    #     self.drivebase.straight(CIRCLE_RADIUS)

    def shake(self, ANGLE):
        self.is_distracting = True
        self.ev3.speaker.beep()
        for i in range(10):
            self.drivebase.turn(ANGLE)
            self.drivebase.tun(-ANGLE)

    def back_and_forth(self, DISTANCE):
        self.is_distracting = True
        self.ev3.speaker.beep()
        self.drivebase.straight(DISTANCE)
        self.drivebase.straight(-2*DISTANCE)
        self.drivebase.straight(DISTANCE)

    def turn_light_off(self, TIME):
        self.is_distracting = True
        self.ev3.light.off()
        wait(TIME)
        self.ev3.light.on(Color.ORANGE)

    def german_speaker(self, WORDS_PER_MINUTE=150, PITCH=45):
        self.is_distracting = True
        self.ev3.speaker.beep()
        self.ev3.speaker.set_speech_options(
            language="de", speed=WORDS_PER_MINUTE, pitch=PITCH)
        self.ev3.speaker.say("""LIEBE WIKIPEDIA-LESER: Diese Woche bitten wir unsere Leser, uns zu
                                helfen. Um unsere Unabhängigkeit zu schützen, werden wir niemals 
                                Werbung schalten. Wir leben von Spenden in Höhe von durchschnittlich
                                15 US-Dollar. Jetzt ist die Zeit, in der wir fragen. Wenn jeder, der
                                dies gerade liest, 3 $ spenden würde, wäre unsere Spendenaktion
                                innerhalb einer Stunde abgeschlossen. Ja, das ist ungefähr der Preis,
                                um einem Programmierer einen Kaffee zu kaufen. Wir sind eine kleine 
                                Non-Profit-Organisation mit Kosten für eine Top-Website: Server, 
                                Personal und Programme. Wikipedia ist etwas Besonderes. Es ist wie
                                eine Bibliothek oder ein öffentlicher Park, wo wir alle hingehen können,
                                um nachzudenken und zu lernen. Wenn Wikipedia für Sie nützlich ist, nehmen
                                Sie sich eine Minute Zeit, um es ein weiteres Jahr lang online und werbefrei
                                zu halten. Vielen Dank.""")


def perform_distractions(self):
    if not self.is_distracting:

    return 0


methods_list = []
