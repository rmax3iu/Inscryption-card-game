package logic.cardLogic;

public class ObstacleLogic extends CardLogic {
    public ObstacleLogic(String name, int hp){
        super(name,hp);
    }

    @Override
    public ObstacleLogic copie(){
        return new ObstacleLogic(super.getName(),super.getHp());
    }
}
