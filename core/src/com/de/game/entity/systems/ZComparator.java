package com.de.game.entity.systems;
import com.de.game.entity.components.TransformComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import java.util.Comparator;
 
 
public class ZComparator implements Comparator<Entity> {
    private ComponentMapper<TransformComponent> cmTrans;
 
    //ZComparator ist für die einordnung in ebenen zuständig, die Z achse des Vector3 des Entitys wird benutzt um die reinfolge fest zu legen
    public ZComparator(){
        cmTrans = ComponentMapper.getFor(TransformComponent.class);
    }
 
    @Override
    public int compare(Entity entityA, Entity entityB) {
        float az = cmTrans.get(entityA).position.z;
        float bz = cmTrans.get(entityB).position.z;
        int res = 0;
        if(az > bz){
            res = 1;
        }else if(az < bz){
            res = -1;
        }
        return res;
    }
}