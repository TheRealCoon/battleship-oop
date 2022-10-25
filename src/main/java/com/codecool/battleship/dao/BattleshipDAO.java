package com.codecool.battleship.dao;

import com.codecool.battleship.player.Score;

import static com.codecool.battleship.utils.Constans.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BattleshipDAO {

    public static List<Score> readHighScoreFromFile() throws IOException {
        String line;
        String[] values;
        List<Score> highScore = new ArrayList<>();
        if (isFileEmpty(DATA_FILE)) throw new NullPointerException("There are no entries in High Scores file!");
        BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
        while ((line = br.readLine()) != null) {
            values = line.split(COLUMN_SEPARATOR);
            Score record = new Score(values[NAME_TABLE_INDEX], Integer.parseInt(values[SCORE_TABLE_INDEX]));
            highScore.add(record);
        }
        return highScore;
    }

    private static boolean isFileEmpty(String dataFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dataFile));
        return (br.readLine()==null || br.readLine().isEmpty());
    }

    public static void writeHighScoreToFile(List<Score> highScore) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE, false));
        for (Score score : highScore) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < score.toTableRow().length; i++) {
                sb.append(score.toTableRow()[i]);
                if (i != score.toTableRow().length - 1)
                    sb.append(COLUMN_SEPARATOR);
            }
            sb.append("\n");
            writer.append(sb.toString());
        }
        writer.close();
    }
}
