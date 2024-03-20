import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        GraphicsPane pane = new GraphicsPane(640, 640);
        window.setContentPane(pane);
        window.pack();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        PhysicsEnvironment env = new PhysicsEnvironment();

//        FixedBlock fb = new FixedBlock(320, 280);
//        pane.addDrawable(fb);
//        env.addObject(fb);

        Round r = new Round();
        r.setPosition(new Vector2(320, 280));
        pane.addDrawable(r);
        env.addObject(r);
        r.setVelocity(new Vector2(0,0));

//        Round r2 = new Round();
//        r2.setPosition(new Vector2(320, 400));
//        r2.setVelocity(new Vector2(0,0));
//        pane.addDrawable(r2);
//        env.addObject(r2);

        double stiffness = 15000, dampF = 4;

        Spring s = new Spring(stiffness, dampF, new Vector2(320,280), new Vector2(320, 285));
        pane.addDrawable(s);
        s.addObject1(r);
        env.addObject(s);

//        Spring s2 = new Spring(100, 1, new Vector2(320,340), new Vector2(320, 400));
//        pane.addDrawable(s2);
//        env.addObject(s2);
//        s2.addObjects(r, r2);


//
        Spring[] ss = new Spring[100];
        ss[0] = s;
        for(int i = 1; i < ss.length; i++) {
            ss[i] = new Spring(stiffness, dampF, ss[i-1].end, Vector2.add(ss[i-1].end, new Vector2(0, 5)));
            Round rtemp = new Round();
            rtemp.radius = 2;
            rtemp.mass = 2;
            rtemp.setPosition(ss[i-1].end);
            pane.addDrawable(rtemp);
            env.addObject(rtemp);
            ss[i-1].addObject2(rtemp);
            ss[i].addObject1(rtemp);
            pane.addDrawable(ss[i]);
            env.addObject(ss[i]);
        }
        Round r2 = new Round();
        r2.setPosition(ss[ss.length-1].end);
        pane.addDrawable(r2);
        env.addObject(r2);

        ss[ss.length-1].addObject2(r2);









    }
}

