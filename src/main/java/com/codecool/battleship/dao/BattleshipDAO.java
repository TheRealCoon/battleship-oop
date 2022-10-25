package com.codecool.battleship.dao;

import com.codecool.battleship.player.Score;
import com.codecool.battleship.utils.Display;

import static com.codecool.battleship.utils.Constans.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BattleshipDAO {

    public static List<Score> readHighScoreFromFile() throws IOException {
        List<Score> highScore = new ArrayList<>();
        BufferedReader br;
        String line;
        String[] values;
            br = new BufferedReader(new FileReader(DATA_FILE));
            if (br.readLine()==null || br.readLine().isEmpty()) throw new NoSuchElementException("There are no entries in High Scores file!");
            while ((line = br.readLine()) != null) {
                values = line.split(COLUMN_SEPARATOR);
                Score record = new Score(values[NAME_TABLE_INDEX], Integer.parseInt(values[SCORE_TABLE_INDEX]));
                highScore.add(record);
            }
        return highScore;
    }

    public static void writeHighScoreToFile(List<Score> highScore){
        //TODO
    }
}
