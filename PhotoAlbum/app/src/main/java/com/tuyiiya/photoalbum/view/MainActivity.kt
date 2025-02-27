package com.tuyiiya.photoalbum.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuyiiya.photoalbum.R
import com.tuyiiya.photoalbum.databinding.ActivityMainBinding
import com.tuyiiya.photoalbum.model.MyImagesAdapter
import com.tuyiiya.photoalbum.viewModel.MyImagesViewModel

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    lateinit var myImagesViewModel: MyImagesViewModel

    lateinit var myImagesAdapter: MyImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myImagesViewModel = ViewModelProvider(this)[MyImagesViewModel::class.java]

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        myImagesAdapter = MyImagesAdapter()
        mainBinding.recyclerView.adapter = myImagesAdapter

        myImagesViewModel.getAllImages().observe(this, { images ->

            // Update UI
            myImagesAdapter.setImage(images)
        })

        mainBinding.floatingActionButton.setOnClickListener {
            // Open Add Image Activity
            val intent = Intent(this, AddImageActivity::class.java)
            startActivity(intent)
        }
    }
}