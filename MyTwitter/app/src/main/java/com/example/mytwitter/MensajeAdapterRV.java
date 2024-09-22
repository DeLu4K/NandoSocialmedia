package com.example.mytwitter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MensajeAdapterRV extends RecyclerView.Adapter<MensajeAdapterRV.MyViewHolder> {

    private ArrayList<Mensaje> listaMensajes;
    private OnButtonClickListener buttonClickListener;

    public MensajeAdapterRV(ArrayList<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes != null ? listaMensajes : new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Mensaje mensaje = listaMensajes.get(position);
        holder.usuario.setText(mensaje.getUsuario());
        holder.texto.setText(mensaje.getTexto());

        int drawableRes = holder.isLiked ?
                R.drawable.baseline_favorite_24 :
                R.drawable.baseline_favorite_border_24;
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(holder.imageView.getContext(), drawableRes));
        holder.contador.setText(String.valueOf(mensaje.getContadorMG()));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.isLiked = !holder.isLiked;

                int newDrawableRes = holder.isLiked ?
                        R.drawable.baseline_favorite_24 :
                        R.drawable.baseline_favorite_border_24;
                holder.imageView.setImageDrawable(ContextCompat.getDrawable(v.getContext(), newDrawableRes));

                mensaje.incrementarContadorMG();
                holder.contador.setText(String.valueOf(mensaje.getContadorMG()));
            }
        });

        holder.papelera.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (buttonClickListener != null) {
                    // Llamada al método onLongButtonClicked del listener
                    buttonClickListener.onLongButtonClicked(position);
                    return true;
                }
                return false;
            }
        });

        holder.fecha.setText(mensaje.getFormattedDate());
        holder.contador.setText(String.valueOf(mensaje.getContadorMG()));
    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public interface OnButtonClickListener {
        void onButtonClicked(int position);
        void onLongButtonClicked(int position);
    }

    public void setOnButtonClickListener(OnButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView usuario, texto;
        ImageView imageView, papelera;
        TextView contador, fecha;
        boolean isLiked;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            usuario = itemView.findViewById(R.id.textViewUser);
            texto = itemView.findViewById(R.id.textViewText);
            imageView = itemView.findViewById(R.id.imageViewCorazon);
            papelera = itemView.findViewById(R.id.imageViewD);
            contador = itemView.findViewById(R.id.textViewLikes);
            fecha = itemView.findViewById(R.id.textViewDate);
            isLiked = false;
        }
    }
}
