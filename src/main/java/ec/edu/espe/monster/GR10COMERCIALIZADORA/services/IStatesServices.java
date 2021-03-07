package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.constances.KeywordsApplication;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.States;

public interface IStatesServices {
	public States getStateByKeyword(KeywordsApplication keywordState);
}
