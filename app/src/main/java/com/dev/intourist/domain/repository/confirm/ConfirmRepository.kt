package com.dev.intourist.domain.repository.confirmimport com.dev.intourist.common.Eitherimport com.dev.intourist.domain.model.confirm.ConfirmAnswerModelimport com.dev.intourist.domain.model.confirm.ConfirmModelimport com.dev.intourist.domain.model.confirm.ResendConfirmModelimport com.dev.intourist.domain.model.recover.RecoverModelimport kotlinx.coroutines.flow.Flowinterface ConfirmRepository {    fun confirm(code: ConfirmModel): Flow<Either<String, ConfirmAnswerModel>>    fun resendConfirm(userName: ResendConfirmModel):Flow<Either<String,List<String>>>    fun resendRecover(email: RecoverModel):Flow<Either<String,List<String>>>}