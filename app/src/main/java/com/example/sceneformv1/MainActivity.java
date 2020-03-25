package com.example.sceneformv1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArFragment arFragment;
    private ModelRenderable bearRenderable, catRenderable, dogRenderable, elephantRenderale, linoRenderable;

    ImageView bear, cat, dog, elephant, lion;

    View arrayView[];
    ViewRenderable name_animal;

    int selected = 1;//Default bear is choose

    ViewRenderable animal_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.scencForm_ux_fragment);

        //View
        bear = (ImageView) findViewById(R.id.bear);
        cat = (ImageView) findViewById(R.id.cat);
        dog = (ImageView) findViewById(R.id.dog);
        elephant = (ImageView) findViewById(R.id.elephant);
        lion = (ImageView) findViewById(R.id.lion);

        setArrayView();
        setClickListener();

        setupModle();

        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                //When user tap on plane, we will add model

                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());

                createModel(anchorNode, selected);

            }
        });


    }

    private void setupModle() {

        ViewRenderable.builder()
                .setView(this, R.layout.name_animal)
                .build()
                .thenAccept(renderable -> name_animal = renderable);

        ModelRenderable.builder()
                .setSource(this, R.raw.bear)
                .build().thenAccept(renderable -> bearRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load dog model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this, R.raw.cat)
                .build().thenAccept(renderable -> catRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load dog model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this, R.raw.dog)
                .build().thenAccept(renderable -> dogRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load dog model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this, R.raw.elephant)
                .build().thenAccept(renderable -> elephantRenderale = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load dog model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this, R.raw.lion)
                .build().thenAccept(renderable -> linoRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unnable to load dog model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
    }

    private void createModel(AnchorNode anchorNode, int selected) {
        if (selected == 1) {
            TransformableNode bear = new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(bearRenderable);
            bear.select();

            addName(anchorNode, bear, "Bear");
        }
        if (selected == 2) {
            TransformableNode cat = new TransformableNode(arFragment.getTransformationSystem());
            cat.setParent(anchorNode);
            cat.setRenderable(catRenderable);
            cat.select();

            addName(anchorNode, cat, "Bear");
        }
        if (selected == 3) {
            TransformableNode dog = new TransformableNode(arFragment.getTransformationSystem());
            dog.setParent(anchorNode);
            dog.setRenderable(dogRenderable);
            dog.select();

            addName(anchorNode, dog, "Bear");
        }
        if (selected == 4) {
            TransformableNode elephant = new TransformableNode(arFragment.getTransformationSystem());
            elephant.setParent(anchorNode);
            elephant.setRenderable(elephantRenderale);
            elephant.select();

            addName(anchorNode, elephant, "Bear");
        }
        if (selected == 5) {
            TransformableNode lion = new TransformableNode(arFragment.getTransformationSystem());
            lion.setParent(anchorNode);
            lion.setRenderable(linoRenderable);
            lion.select();

            addName(anchorNode, lion, "Bear");
        }
    }

    private void addName(AnchorNode anchorNode, TransformableNode model, String name) {
        TransformableNode nameView = new TransformableNode(arFragment.getTransformationSystem());
        nameView.setLocalPosition(new Vector3(0f, model.getLocalPosition().y+0.5f, 0));
        nameView.setParent(anchorNode);
        nameView.setRenderable(name_animal);
        nameView.select();

        //set text
        TextView txt_name = (TextView)name_animal.getView();
        txt_name.setText(name);
        //Click to text view to remove aanimal
        txt_name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                anchorNode.setParent(null);
            }
        });

    }

    private void setClickListener() {
        for (int i = 0; i < arrayView.length; i++) {
            arrayView[i].setOnClickListener(this);
        }
    }

    private void setArrayView() {
        arrayView = new View[]{
                bear, cat, dog, elephant, lion
        };
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bear) {
            selected = 1;
            setBackground(view.getId());
        } else if (view.getId() == R.id.cat) {
            selected = 2;
            setBackground(view.getId());
        } else if (view.getId() == R.id.dog) {
            selected = 3;
            setBackground(view.getId());
        } else if (view.getId() == R.id.elephant) {
            selected = 4;
            setBackground(view.getId());
        } else if (view.getId() == R.id.lion) {
            selected = 5;
            setBackground(view.getId());
        }

    }

    private void setBackground(int id) {
        for (int i=0; i<arrayView.length; i++){
            if(arrayView[i].getId()== id){
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"));//set background for selected animail
            }else {
                arrayView[i].setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

}
