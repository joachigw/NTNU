from player import Player

class Table:
    def ___init___(self, players):
        print("haha")

player1 = Player((0), "Jens")
player1.game_scores = [[10]]
player2 = Player((0), "Kristians")
player2.game_scores = [[666]]
player3 = Player((0), "Marius")
player3.game_scores = [[5]]
player4 = Player((0), "Haakon")
players = [player1, player2, player3, player4]

d = {1: ["Python", 33.2, 'UP'],
    2: ["Java", 23.54, 'DOWN'],
    3: ["Ruby", 17.22, 'UP'],
    10: ["Lua", 10.55, 'DOWN'],
    5: ["Groovy", 9.22, 'DOWN'],
    6: ["C", 1.55, 'UP']
    }

print ("{:<8} {:<15} {:<10} {:<15} {:<10}".format('Pos','Name','GameScore','Average Time', 'Average GameScore'))
for player in players:
    i = 1
    print ("{:<8} {:<15} {:<10} {:<15} {:<10}".format(i , player.player_name, player.get_total_game_score(), player.get_average_game_time(), player.get_average_game_score()))
    i += 1

print ("{:<8} {:<15} {:<10} {:<15} {:<10}".format('Pos','Name','GameScore','Average Time', 'Average GameScore'))
for i in range [1, 2, 3 , 4]:
    print ("{:<8} {:<15} {:<10} {:<15} {:<10}".format(i+1 , players[i].player_name, players[i].get_total_game_score(), players[i].get_average_game_time(), players[i].get_average_game_score()))
    

    
