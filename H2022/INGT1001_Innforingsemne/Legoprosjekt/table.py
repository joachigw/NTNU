# Python program to create a table
from player import Player
from tkinter import *


class Table:  # klassen Table bruker tkinter import for å skape en tabell som dukker opp på skjermen

    def __init__(self, root, players):
        addCellEntryToGrid(self, 0, 0, "Place", 'red')
        addCellEntryToGrid(self, 0, 1, "Name", 'red')
        addCellEntryToGrid(self, 0, 2, "Score", 'red')
        addCellEntryToGrid(self, 0, 3, "Average Time", 'red')
        addCellEntryToGrid(self, 0, 4, "Average GameScore", 'red')

        # code for creating table
        for i in range(total_rows):
            addCellEntryToGrid(self, i+1, 0, i+1)
            addCellEntryToGrid(self, i+1, 1, players[i].player_name)
            addCellEntryToGrid(self, i+1, 2, players[i].get_total_game_score())
            addCellEntryToGrid(
                self, i+1, 3, players[i].get_average_game_time())
            addCellEntryToGrid(
                self, i+1, 4, players[i].get_average_game_score())
# take the data


def addCellEntryToGrid(self, row_nr, column_nr, toPlot, color='blue'):
    """Legger en liste av variabler inn i en tabell (usortert), brukes når vi skaper en Table-objekt. \n 
    row_nr og column_nr sier hvor i tabellen du legger data inn. \n
    toPlot er hvilken verdi du setter inn \n
    color er fargen på teksten"""
    self.e = Entry(root, width=35, fg=color,
                   font=('Comic Sans', 16, 'bold'))
    self.e.grid(row=row_nr, column=column_nr)
    self.e.insert(END, toPlot)

#player1 = Player((0), "Jens")
#player1.game_scores = [[10]]
#player2 = Player((0), "Kristians")
#player2.game_scores = [[666]]
#player3 = Player((0), "Marius")
#player3.game_scores = [[5]]
#player4 = Player((0), "Haakon")
#players = [player1, player2, player3, player4]


total_columns = 4
total_rows = 4
root = 0


def createScoreTable(players):
    """Denne funksjonen sorterer listen players, der de er sortert fra høyeste scoren til den laveste \n
    eneste variablen for funksjonene er altså en liste som heter players,
    funksjonen gjør vinduen for tabellen større, resizable og gir en gratulasjonsmelding til personen i første plass"""
    players.sort(key=lambda x: x.get_average_game_score(), reverse=True)
    root = Tk()
    t = Table(root, players)
    root.resizable(True, True)
    root.title("Match Results")
    root.geometry('1920x1080')
    congratulations = Label(root, text="Congratulations {}, you are a winner!".format(
        players[0].player_name), fg='black', font=('Arial', 50, 'bold'))
    congratulations.place(x=300, y=500)
    root.mainloop()

# create root window
