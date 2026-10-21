package com.professionalit.service;

import com.professionalit.grpc.WalletRequest;
import com.professionalit.grpc.WalletResponse;
import com.professionalit.grpc.WalletServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class WalletGrpcService extends WalletServiceGrpc.WalletServiceImplBase {
	
    @Override
    public void validateWallet(
            WalletRequest request,
            StreamObserver<WalletResponse> responseObserver) {
        System.out.println(request.getUserId());
        System.out.println(request.getAmount());

        String accountNumber = request.getUserId();
        double amount = request.getAmount();

        System.out.println(
                "Wallet validation: accountNumber="
                        + accountNumber
                        + ", amount="
                        + amount
        );

        // Demo wallet balance
        double availableBalance = 500000;

        boolean valid = amount <= availableBalance;

        WalletResponse response;

        if (valid) {

            response = WalletResponse.newBuilder()
                    .setValid(true)
                    .setMessage(
                            "Wallet validation successful. "
                            + "Available balance: ₹"
                            + availableBalance
                    )
                    .build();

        } else {

            response = WalletResponse.newBuilder()
                    .setValid(false)
                    .setMessage(
                            "Insufficient wallet balance. "
                            + "Required: ₹"
                            + amount
                            + ", Available: ₹"
                            + availableBalance
                    )
                    .build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}