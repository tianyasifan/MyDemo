package com.txt.base;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.AsyncTask;

public abstract class BaseTask<Result,T> extends AsyncTask<String, Void, Result> {
	private WeakReference<IBaseTaskListener> mListener;
	private Context mContext;
	public BaseTask(IBaseTaskListener listener){
		mListener = new WeakReference<IBaseTaskListener>(listener);
		if(listener instanceof Context){
			mContext = ((Context)listener).getApplicationContext();
		}
	}

	@Override
	protected Result doInBackground(String... params) {
		// TODO Auto-generated method stub
		return doMyBackground(params);
	}

	abstract Result doMyBackground(String... params);
	
	@Override
	protected void onPostExecute(Result result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(mListener.get()!=null){
		
		}
	}
	
	public interface IBaseTaskListener{
		void onSuccess();
		void onFailed();
	}
}
