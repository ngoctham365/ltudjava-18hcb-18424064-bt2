/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import ltudjava.hcb.bt2.dto.Score;
import ltudjava.hcb.bt2.dao.ScoreDAO;

/**
 *
 * @author Jossion
 */
public class ScoreBUS {

    static Score getByID(int scoreId) {
        return new ScoreDAO().getById(scoreId);
    }
    
}
